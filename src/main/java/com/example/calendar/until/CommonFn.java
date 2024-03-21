package com.example.calendar.until;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.example.bean.ResponseError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Size;

public class CommonFn {

	public static Pageable getPageable(Integer page, Integer size, String[] textSort) {
		List<Order> list = new ArrayList<Order>();

		String[] sortDefault = { "createdAt@desc", "id" };
		if (textSort == null || textSort.length == 0) {
			textSort = sortDefault;
		}

		for (String orderSort : textSort) {
			if (orderSort.contains("@")) {
				String[] sortSplitStrings = orderSort.split("@");
				list.add(new Order(sortDirection(sortSplitStrings[1]), sortSplitStrings[0]));
			} else {
				list.add(new Order(sortDirection(""), orderSort));
			}
		}
		if (size == -1) {
			size = Integer.MAX_VALUE;
		}

		return PageRequest.of(page, size, Sort.by(list));
	}

	public static Sort.Direction sortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc") || direction.equals("DESC")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	public static String formatterDate(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		return formatter.format(date);

	}

	public static String convertObjectToJson(Object object) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer();
		return ow.writeValueAsString(object);
	}

	public static Map<String, Object> convertObjectToHashmap(Object obj) {
		if (obj == null)
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
		});
	}
//	
//	public static Object objectMapper(Object object, Class<?> ){
//    	ObjectMapper mapper = new ObjectMapper();
//    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//    	PersonalInfo personInfo = mapper.convertValue(object, PersonalInfo.class);
//        return personInfo;
//    }

	public static String convertTextSearch(String textSearch) {
		if (textSearch != null)
			textSearch = textSearch.replaceAll("%", "!%").replaceAll("_", "!_");
		return textSearch;
	}

	// kiểm tra xem giá trị có nằm trong enum không
	public static <T extends Enum<T>> boolean containsEnumValue(String value, Class<T> enumClass) {
		for (T enumConstant : enumClass.getEnumConstants()) {
			if (enumConstant.name().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static <T> List<ResponseError> checkValidateEntityImport(T obj, int row, Map<?, ?> mapCodeErr) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(obj);
		List<ResponseError> listErr = new ArrayList<>();
		if (!violations.isEmpty()) {
			for (ConstraintViolation<T> violation : violations) {
				ResponseError error = new ResponseError();
				error.setRowNumber(row);
				String colNameEN = violation.getPropertyPath().toString().toUpperCase();
				Optional<?> colNameVN = mapCodeErr.entrySet().stream()
						.filter(entry -> colNameEN.equalsIgnoreCase(entry.getKey().toString())).map(Map.Entry::getValue)
						.findFirst();
				if (colNameVN.isPresent())
					error.setColumn(colNameVN.get().toString().toUpperCase());
				else
					error.setColumn(colNameEN);
				error.setMessageError(violation.getMessage());
				error.setValueError(violation.getInvalidValue());
				String fieldName = violation.getPropertyPath().toString();
				error = convertMessageSizrForEntity(error, obj, fieldName);
				listErr.add(error);
			}
		}
		return listErr;

	}

	public static Date convertStringToDate(String date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> ResponseError convertMessageSizrForEntity(ResponseError responseError, Object obj,
			String fieldName) {
		String[] messages = responseError.getMessageError().split("-");
		String message = "";
		int i = 0;
		while (i < messages.length) {
			message += messages[i] + " ";
			i++;
		}
		Field field = getField(obj.getClass(), fieldName);
		Size sizeAnnotation = field.getAnnotation(Size.class);
		if (sizeAnnotation != null) {
			message = message.replace("{min}", String.valueOf(sizeAnnotation.min()));
			message = message.replace("{max}", String.valueOf(sizeAnnotation.max()));
		}
		responseError.setMessageError(message);
		return responseError;
	}

	private static Field getField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String generateRandomPassword() {
		String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercaseChars = "abcdefghijklmnopqrstuvwxyz123456789";
		String number = "123456789";
		String specialChars = "!@#$%^&*()-=_";

		String allChars = uppercaseChars + lowercaseChars + specialChars;

		StringBuilder password = new StringBuilder();

		// Add at least one uppercase character
		password.append(uppercaseChars.charAt((int) (Math.random() * uppercaseChars.length())));

		// Add at least one lowercase character
		password.append(lowercaseChars.charAt((int) (Math.random() * lowercaseChars.length())));

		// Add at least one special character
		password.append(specialChars.charAt((int) (Math.random() * specialChars.length())));

		// Add at least one number
		password.append(number.charAt((int) (Math.random() * specialChars.length())));

		// Fill the rest of the password with random characters from allChars
		for (int i = 3; i < 12; i++) { // Assuming a total length of 12 characters
			password.append(allChars.charAt((int) (Math.random() * allChars.length())));
		}

		return password.toString();
	}

}