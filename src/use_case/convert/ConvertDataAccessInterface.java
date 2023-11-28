package use_case.convert;

public interface ConvertDataAccessInterface {
    boolean existsByCode(String identifier);

    String get(String code);
}
