package use_case.search_exchangerate;

public interface SearchDataAccessInterface {
    boolean existsByCode(String identifier);
    String get(String code);


}
