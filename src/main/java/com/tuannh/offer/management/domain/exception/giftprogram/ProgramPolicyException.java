package com.tuannh.offer.management.domain.exception.giftprogram;

public class ProgramPolicyException extends Exception {
    public ProgramPolicyException() { }
    public ProgramPolicyException(Throwable throwable) { super(throwable); }
    public static class PolicyNotFound extends ProgramPolicyException {
        public PolicyNotFound() { }
        public PolicyNotFound(Throwable throwable) { super(throwable); }
    }

    public static class CouldNotGetPolicy extends ProgramPolicyException {
        public CouldNotGetPolicy() { }
        public CouldNotGetPolicy(Throwable throwable) { super(throwable); }
    }
}
