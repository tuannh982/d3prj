package com.tuannh.offer.management.commons.args;

import com.tuannh.offer.management.commons.assertion.Assertions;
import lombok.NonNull;
import org.apache.commons.lang3.ClassUtils;

@SuppressWarnings("rawtypes")
public class ArgsClass {
    protected int argc;
    protected Object[] args;
    protected Class[] argsType;

    public ArgsClass(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        Assertions.equalChecks(argc, args.length);
        Assertions.equalChecks(argc, argsType.length);
        this.argc = argc;
        this.args = args;
        this.argsType = argsType;
        for (int i = 0; i < argc; i++) {
            if (!ClassUtils.isAssignable(args[i].getClass(), argsType[i], true)) {
                throw new IllegalArgumentException();
            }
        }
    }
}
