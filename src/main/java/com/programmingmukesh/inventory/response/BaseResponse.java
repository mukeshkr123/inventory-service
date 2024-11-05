    package com.programmingmukesh.inventory.response;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class BaseResponse<T> {
        private boolean success;
        private String error;
        private T data;

        public static <T> BaseResponse<T> success(T data) {
            return new BaseResponse<>(true, null, data);
        }

        public static <T> BaseResponse<T> error(String error) {
            return new BaseResponse<>(false, error, null);
        }
    }
