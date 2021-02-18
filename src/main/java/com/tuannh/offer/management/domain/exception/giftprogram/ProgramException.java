package com.tuannh.offer.management.domain.exception.giftprogram;

public class ProgramException extends Exception {
    public ProgramException() { }
    public ProgramException(Throwable throwable) { super(throwable); }

    public static class ProgramNotFound extends ProgramException {
        public ProgramNotFound() { }
        public ProgramNotFound(Throwable throwable) { super(throwable); }
    }

    public static class CouldNotGetProgram extends ProgramException {
        public CouldNotGetProgram() { }
        public CouldNotGetProgram(Throwable throwable) { super(throwable); }
    }
}
