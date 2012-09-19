package de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

public class FilterUtility {

	public interface Predicate<T, E> {
		boolean apply(T t);

		T parse(E e) throws ParseException;
	}

	public static <T, E> ArrayList<ArrayList<E>> filter(
			List<ArrayList<E>> list, Predicate<T, E> predicate, int index) {
		ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
		for (ArrayList<E> element : list) {
			try {
				if (predicate.apply(predicate.parse(element.get(index)))) {
					result.add(element);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Predicate<Double, String> getIntOperatorPredicate(
			IntOperations operation, final Double value) {
		switch (operation) {
		case LessThan:
			return new Predicate<Double, String>() {

				@Override
				public boolean apply(Double type) {
					return type < value;
				}

				@Override
				public Double parse(String e) {
					return Double.parseDouble(e);
				}
			};

		case LessThanEqualTo:

			return new Predicate<Double, String>() {

				@Override
				public boolean apply(Double type) {
					return type <= value;
				}

				@Override
				public Double parse(String e) {
					return Double.parseDouble(e);
				}
			};

		case EqualTo:

			return new Predicate<Double, String>() {

				@Override
				public boolean apply(Double type) {
					return type.equals(value);
				}

				@Override
				public Double parse(String e) {
					return Double.parseDouble(e);
				}
			};

		case GreaterThan:
			return new Predicate<Double, String>() {

				@Override
				public boolean apply(Double type) {
					return type > value;
				}

				@Override
				public Double parse(String e) {
					return Double.parseDouble(e);
				}
			};

		case GreaterThanEQualTo:
			return new Predicate<Double, String>() {

				@Override
				public boolean apply(Double type) {
					return type >= value;
				}

				@Override
				public Double parse(String e) {
					return Double.parseDouble(e);
				}
			};

		default:
			break;
		}
		return null;

	}

	public static Predicate<String, String> getTextOperatorPredicate(
			TextOperations textOperation, final String value) {
		switch (textOperation) {
		case Contains:

			return new Predicate<String, String>() {

				@Override
				public boolean apply(String type) {
					return type.contains(value);
				}

				@Override
				public String parse(String e) {
					return e;
				}
			};
		default:
			break;
		}
		return null;
	}

	public static Predicate<Date, String> getDateOperatorPredicate(
			DateOperations dateOperation, final String operand) {

		final DateTimeFormat fmt = DateTimeFormat.getFormat("MM/dd/yyyy");
		final Date value =fmt.parse(operand);

		switch (dateOperation) {
		case Before:

			return new Predicate<Date, String>() {

				@Override
				public Date parse(String e) throws ParseException {
					return fmt.parse(e);
				}

				@Override
				public boolean apply(Date t) {
					return t.before(value);
				}
			};
		case After:
			return new Predicate<Date, String>() {

				@Override
				public boolean apply(Date t) {

					return t.after(value);
				}

				@Override
				public Date parse(String e) throws ParseException {

					return fmt.parse(e);
				}
			};
		case On:
			return new Predicate<Date, String>() {

				@Override
				public boolean apply(Date t) {
					return t.equals(value);
				}

				@Override
				public Date parse(String e) throws ParseException {
					return fmt.parse(e);
				}
			};

		default:
			break;
		}
		return null;
	}
}
