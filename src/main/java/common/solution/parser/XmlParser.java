package common.solution.parser;

public interface XmlParser<T> {
    T parseFile(String file) throws Exception;
}
