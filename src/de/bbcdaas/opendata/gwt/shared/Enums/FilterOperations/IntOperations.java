package de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations;

public enum IntOperations {
	LessThan("<"), LessThanEqualTo("<="), EqualTo("="), GreaterThan(">"), GreaterThanEQualTo(
			">=");
	private IntOperations(String name) {
		this.name = name;
	}

	private final String name;

}
