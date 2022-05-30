package com.uca.spring.jqgrid;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.spring.jqgrid.JqgridMeta.Column;

import java.util.ArrayList;
import java.util.Iterator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Maps a jQgrid JSON query to a {@link JqgridFilter} instance
 */
public class JqgridObjectMapper {

    public static final String[] EXCLUDE_FIELDS = {"\"rn\"", "\"\"",
        "\"actionLink\"", "\"Acciones\"", "Acciones"};

    private final static Logger logger = LoggerFactory.getLogger(JqgridObjectMapper.class);

    public static JqgridFilter map(String jsonString) {

        if (jsonString != null) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                return mapper.readValue(jsonString, JqgridFilter.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public static JqgridFilter map(String jsonString, String orderBy, String orderType) {

        if (jsonString != null) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                JqgridFilter filter = mapper.readValue(jsonString, JqgridFilter.class);
                filter.setSortBy(orderBy);
                filter.setSortType(orderType);
                return filter;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public static JqgridMeta mapModel(String jsonStringModel,
            String jsonStringNames) {
        ArrayList<Column> colNames = new ArrayList<Column>(0);

        ArrayList<Column> colModel = new ArrayList<Column>(0);

        if (jsonStringModel != null) {
            ObjectMapper mapper = new ObjectMapper();

            try {

                JsonNode rootNodeModel = mapper.readTree(jsonStringModel);
                for (Iterator<JsonNode> it = rootNodeModel.elements(); it
                        .hasNext();) {
                    JsonNode node = it.next();

                    if (!isNullOrEmpty(node.get("name"))
                            && !in(node.get("name").toString(),
                                    EXCLUDE_FIELDS)) {
                        JqgridMeta.Column column = new JqgridMeta.Column();
                        column.setName(node.get("name").toString()
                                .replace("\"", ""));

                        column.setWidth(node.get("width").toString()
                                .replace("\"", ""));

                        colModel.add(column);
                    }

                }

                JsonNode rootNodeNames = mapper.readTree(jsonStringNames);
                for (Iterator<JsonNode> it = rootNodeNames.elements(); it
                        .hasNext();) {
                    JsonNode node = it.next();
                    if (!isNullOrEmpty(node.toString())
                            && !in(node.toString(), EXCLUDE_FIELDS)) {
                        JqgridMeta.Column column = new JqgridMeta.Column();
                        column.setDescription(replaceAllTilde(node.toString().replace("\"", "")));
                        colNames.add(column);
                    }
                }

                for (int i = 0; i < colModel.size(); i++) {
                    Column column = colModel.get(i);
                    if (colNames.get(i) == null || colNames.get(i).getDescription() == null) {
                        column.setDescription("");
                    } else {
                        column.setDescription(colNames.get(i).getDescription());
                    }
                }

                JqgridMeta meta = new JqgridMeta();
                meta.setProperties(colModel);

                return meta;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public static boolean in(Object obj, Object[] listing) {
        return indexOf(obj, listing) != -1;
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null || obj.toString().length() < 1
                || obj.toString().equals("")) {
            return true;
        }
        return false;
    }

    public static int indexOf(Object obj, Object[] listing) {
        for (int i = 0; i < listing.length; i++) {
            if (obj.equals(listing[i])) {
                return i;
            }
        }

        if (obj instanceof java.lang.String) {
            for (int i = 0; i < listing.length; i++) {
                if (obj.toString().equals(listing[i])) {
                    return i;
                }
            }
        }// for String

        return -1;
    }

    public static String replaceAllTilde(String str) {
        String[] REPLACE_PATTERN = {"&aacute;", "&eacute;", "&iacute;", "&oacute;", "&uacute;", "&Aacute;", "&Eacute;", "&Iacute;", "&Oacute;", "&Uacute;"};
        String[] REPLACE_STR = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"};
        String result = str;
        if (result != null) {
            for (int i = 0; i < REPLACE_PATTERN.length; i++) {
                result = result.replaceAll(REPLACE_PATTERN[i], REPLACE_STR[i]);
            }
            return result;
        } else {
            return result;
        }
    }
}
