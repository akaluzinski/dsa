package eu.kaluzinski.mf_importer;

import static java.util.stream.Collectors.toMap;

import eu.kaluzinski.mf_importer.emums.Header;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public interface HeaderMapper {

  List<Header> mapHeaders(List<String> headers);

  default Map<Header, Integer> getHeaderIndexes(List<Header> headers) {
    return IntStream.range(0, headers.size())
        .boxed()
        .collect(toMap(
            headers::get,
            Function.identity()
        ));
  }
}
