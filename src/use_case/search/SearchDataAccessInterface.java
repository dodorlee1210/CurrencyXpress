package use_case.search;

public interface SearchDataAccessInterface {
    boolean existsByCode(String identifier);
    String get(String code);

}
