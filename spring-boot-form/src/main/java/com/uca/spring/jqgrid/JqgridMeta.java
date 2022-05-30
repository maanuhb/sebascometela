package com.uca.spring.jqgrid;


import java.util.ArrayList;

public class JqgridMeta {

	private ArrayList<Column> properties;
	

	public ArrayList<Column> getProperties() {
		return properties;
	}


	public void setProperties(ArrayList<Column> properties) {
		this.properties = properties;
	}


	/**
	 * Inner class containing field rules
	 */
	public static class Column {
		private String name;
		private String description;
		private String width;

		public Column() {
		}

		 
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getWidth() {
			return width;
		}

		public void setWidth(String width) {
			this.width = width;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("name: -->" + this.name + ", ");
			sb.append("description: -->" + this.description+ ", ");
			sb.append("width: -->" + this.width + ", ");

			return sb.toString();
		}

	}
}


