package com.real.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatcherImpl implements Matcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(MatcherImpl.class);

  private CsvStream movieDb;
  private CsvStream actorAndDirectorDb;
  Map<String, String> movieList;
  Map<String, String> externalIdList = new HashMap<>();

  public MatcherImpl(CsvStream movieDb, CsvStream actorAndDirectorDb) {
    LOGGER.info("importing database");
    // TODO implement me
    this.movieDb = movieDb;
    this.actorAndDirectorDb = actorAndDirectorDb;
    movieList = movieDb.getDataRows().map(line->line.split(","))
            .collect(Collectors.toMap(part -> part[1], part -> part[0], (r1, r2) -> r1));
    LOGGER.info("database imported");
  }

  @Override
  public List<IdMapping> match(DatabaseType databaseType, CsvStream externalDb) {
    // TODO implement me

    List<IdMapping> list = externalDb.getDataRows()
            .map(line -> line.split(","))
            .filter(part -> movieList.containsKey(part[4]))
            .filter(part -> checkURL(part[part.length-1], databaseType, part[4]))
            .map(part -> {
              IdMapping idMapping = new IdMapping(Integer.parseInt(movieList.get(part[4])), getKeyByValue(externalIdList, part[4]));
              return idMapping;
            })
            .collect(Collectors.toList());

    HashSet<Integer> set = new HashSet<>();
    list.removeIf(e -> !set.add(e.getInternalId()));

    return list;
  }

  public boolean checkURL(String URL, DatabaseType databaseType, String title) {
    //System.out.println("URL: "+ URL);
    boolean flag = StringUtils.containsIgnoreCase(URL, databaseType.name());
    if (flag) {
      int index = URL.lastIndexOf('/');
      externalIdList.put(URL.substring(index+1), title);
    }
    return flag;
  }

  public String getKeyByValue(Map<String, String> map, String value) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

}
