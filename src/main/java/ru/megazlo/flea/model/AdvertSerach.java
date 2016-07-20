package ru.megazlo.flea.model;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/*@NamedStoredProcedureQuery(
		name = "searchAdvert",
		procedureName = "search_advert",
		resultClasses = {AdvertSerach.class},
		parameters = {
				@StoredProcedureParameter(name = "textQuery", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "lastName", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "departmentId", type = Integer.class, mode = ParameterMode.IN)
		}
)*/
public class AdvertSerach {
}
