package com.uca.spring.jqgrid;



import java.util.ArrayList;
import java.util.HashMap;


public class JqgridFilter {

	public static final String AND_OPERATOR = "AND";
	public static final String OR_OPERATOR = "OR";
	public static final String SORT_ASC = "asc";
	public static final String SORT_DESC = "desc";
	/*
	 * 
	 * Group Operator AND, OR
	 * 
	 * 
	 * Not Implemented, for practical purposes the programmer must define the
	 * rules for searching Rules Operator
	 * ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	 * The corresponding texts are in language file and mean the following:
	 * ['equal','not equal', 'less', 'less or equal','greater','greater or
	 * equal', 'begins with','does not begin with','is in','is not in','ends
	 * with','does not end with','contains','does not contain']
	 */

	private String source;
	private String groupOp;
	private String sortBy;
	private String sortType = SORT_ASC;
	private ArrayList<Rule> rules;

	public JqgridFilter() {
		super();
	}

	public JqgridFilter(String source) {
		super();
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

        
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("source:-->" + this.source + ", ");
		sb.append("groupOp:-->" + this.groupOp + ", ");

		for (Rule rule : rules) {
			if (!isNullOrEmpty(rule)) {
				sb.append(rule.toString());
			}
		}

		return super.toString();
	}

	/**
	 * Inner class containing field rules
	 */
	public static class Rule {
		private String junction;
		private String field;
		private String op;
		private String data;

		public Rule() {
		}

		public Rule(String junction, String field, String op, String data) {
			super();
			this.junction = junction;
			this.field = field;
			this.op = op;
			this.data = data;
		}

		public String getJunction() {
			return junction;
		}

		public void setJunction(String junction) {
			this.junction = junction;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOp() {
			return op;
		}

		public void setOp(String op) {
			this.op = op;
		}

		public String getData() {
			if(data.equals("") || data==null){
				return null;
			}
			else{
				return data;
			}
			
		}

		public void setData(String data) {
			this.data = data;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("junction: -->" + this.junction + ", ");
			sb.append("field: -->" + this.field + ", ");
			sb.append("op: -->" + this.op + ", ");
			sb.append("data: -->" + this.data + ", ");

			return sb.toString();
		}

	}

	public String getFieldValue(String fieldName) {

		if (!isNullOrEmpty(getRules())) {

			for (JqgridFilter.Rule rule : getRules()) {
				if (rule.getField().equals(fieldName)) {
					return rule.getData();
				}
			}
		}
		return null;

	}

	public String getFieldValueAsZero(String fieldName) {
		if (!isNullOrEmpty(getRules())) {
			for (JqgridFilter.Rule rule : getRules()) {
				if (rule.getField().equals(fieldName)) {
					return rule.getData();
				}
			}
		}
		return "0";

	}
	
	public String getFieldValueAsEmpty(String fieldName) {
		if (!isNullOrEmpty(getRules())) {
			for (JqgridFilter.Rule rule : getRules()) {
				if (rule.getField().equals(fieldName)) {
					return rule.getData();
				}
			}
		}
		return "";

	}

	public HashMap<String, String> getPropertiesAsMap() {
		HashMap<String, String> values = new HashMap<String, String>();

		for (JqgridFilter.Rule rule : getRules()) {
			values.put(rule.getField(), rule.getData());

		}
		return values;

	}

	
	public void addRule(String junction, String field, String op, String data) {
		if (isNullOrEmpty(this.getRules())){
			this.rules = new ArrayList<JqgridFilter.Rule>();
			
		}
		
		this.getRules().add(new Rule(junction,field,op,data));
		
	}
        
        public  boolean isNullOrEmpty(Object obj) {
		if (obj == null || obj.toString().length() < 1
				|| obj.toString().equals(""))
			return true;
		return false;
	}
        
        public static String getField(String filters,String column){
            String field=null;
            if (filters != null && !filters.equals("")) {

                JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
                for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
                    if (rule.getField().equals(column)) {
                        field = rule.getData();
                    }
                }

            }
            return field;
        }
        
        public static Integer getFieldInteger(String filters,String column){
            if (filters != null && !filters.equals("")) {
                JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
                try {
                    for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
                        if (rule.getField().equals(column)) {
                            return new Integer(rule.getData());
                        }
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            
            return null;
        }
}


