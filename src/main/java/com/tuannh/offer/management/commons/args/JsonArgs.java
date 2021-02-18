package com.tuannh.offer.management.commons.args;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ClassUtils;

import javax.activation.UnsupportedDataTypeException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JsonArgs {
    private Class cls;
    private int argc;
    private List<Class> classes;
    private List<JsonNode> args;

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final JavaType mapType =
            JSON_MAPPER.getTypeFactory().constructType(new TypeReference<Map<String, JsonNode>>() {});
    private static final JavaType listType =
            JSON_MAPPER.getTypeFactory().constructType(new TypeReference<List<JsonNode>>() {});

    public static Object from(String json)
            throws IOException,
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException {
        return from(JSON_MAPPER.readTree(json), null);
    }

    public static boolean isArgsClass(JsonNode jsonNode) {
        return jsonNode.has("cls") &&
                jsonNode.has("argc") &&
                jsonNode.has("classes") &&
                jsonNode.has("args");
    }

    public static boolean isMarkerClass(JsonNode jsonNode) {
        return jsonNode.has("marker");
    }

    @SuppressWarnings("unchecked")
    public static Object fromBaseClass(JsonNode jsonNode, Class optClass) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (optClass != null && optClass != Map.class && optClass != List.class) {
            return JSON_MAPPER.treeToValue(jsonNode, optClass);
        }
        switch (jsonNode.getNodeType()) {
            case NULL:
                return null;
            case STRING:
                return jsonNode.textValue();
            case POJO:
                return ((POJONode)jsonNode).getPojo();
            case BINARY:
                return jsonNode.binaryValue();
            case NUMBER:
                if (jsonNode.isShort()) {
                    return jsonNode.shortValue();
                } else if (jsonNode.isInt()) {
                    return jsonNode.intValue();
                } else if (jsonNode.isFloat()) {
                    return jsonNode.floatValue();
                } else if (jsonNode.isDouble()) {
                    return jsonNode.doubleValue();
                } else if (jsonNode.isLong()) {
                    return jsonNode.longValue();
                } else {
                    throw new UnsupportedDataTypeException();
                }
            case BOOLEAN:
                return jsonNode.booleanValue();
            case ARRAY: {
                List<JsonNode> lst = JSON_MAPPER.readValue(
                        JSON_MAPPER.treeAsTokens(jsonNode),
                        listType
                );
                List<Object> ret = new ArrayList<>();
                for (JsonNode node : lst) {
                    ret.add(from(node, null));
                }
                return ret;
            }
            case OBJECT: {
                Map<String, JsonNode> mp = JSON_MAPPER.readValue(
                        JSON_MAPPER.treeAsTokens(jsonNode),
                        mapType
                );
                Map<String, Object> ret = new HashMap<>();
                for (Map.Entry<String, JsonNode> entry : mp.entrySet()) {
                    ret.put(entry.getKey(), from(entry.getValue(), null));
                }
                return ret;
            }
            case MISSING:
            default:
                throw new IllegalStateException("json type not supported");
        }
    }

    @SuppressWarnings("unchecked")
    public static Object fromArgsClass(int argc, Class clazz, JsonArgs jsonArgs) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, IOException {
        Constructor<?> constructor = clazz.getConstructor(int.class, Object[].class, Class[].class);
        Object[] params = new Object[argc];
        Class[] classes = new Class[argc];
        for (int i = 0; i < argc; i++) {
            classes[i] = jsonArgs.getClasses().get(i);
            params[i] = from(jsonArgs.getArgs().get(i), classes[i]);
        }
        return constructor.newInstance(argc, params, classes);
    }

    @SuppressWarnings("unchecked")
    public static Object from(JsonNode jsonNode, Class optClass) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (jsonNode.isObject() && isMarkerClass(jsonNode)) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            String marker = objectNode.get("marker").textValue();
            objectNode.remove("marker");
            Class markerClass = MarkerClass.markerTable.get(marker);
            return JSON_MAPPER.treeToValue(objectNode, markerClass);
        }
        if (!isArgsClass(jsonNode)) return fromBaseClass(jsonNode, optClass);
        JsonArgs jsonArgs = JSON_MAPPER.treeToValue(jsonNode, JsonArgs.class);
        Class clazz = jsonArgs.getCls();
        Constructor<?> constructor = null;
        Object instance = null;
        int argc = jsonArgs.getArgc();
        if (argc == 0) {
            constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } else {
            if (ClassUtils.isAssignable(clazz, ArgsClass.class)) {
                return fromArgsClass(argc, clazz, jsonArgs);
            } else {
                constructor = clazz.getConstructor(Object[].class);
                Object[] params = new Object[argc];
                for (int i = 0; i < argc; i++) {
                    params[i] = from(jsonArgs.getArgs().get(i), null);
                }
                instance = constructor.newInstance(params);
            }
        }
        return instance;
    }
}
