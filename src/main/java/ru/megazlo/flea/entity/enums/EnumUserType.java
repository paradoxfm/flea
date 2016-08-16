package ru.megazlo.flea.entity.enums;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/** Created by iGurkin on 16.08.2016. */
@SuppressWarnings("unchecked")
class EnumUserType<K, E extends Enum<E> & IValueEnum<K>> implements UserType {

	private Class<E> clazz;

	EnumUserType(Class<E> c) {
		this.clazz = c;
	}

	private static final int[] SQL_TYPES = {Types.INTEGER, Types.VARCHAR};

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
		K value = (K) resultSet.getObject(names[0]);
		return !resultSet.wasNull() ? findMappedConstant(value) : null;
	}

	private E findMappedConstant(K val) {
		for (E e : clazz.getEnumConstants()) {
			if (e.getVal() == val) {
				return e;
			}
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement prepStat, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {
		if (null == value) {
			prepStat.setNull(index, Types.OTHER);
		} else {
			prepStat.setObject(index, ((E) value).getVal());
		}
	}

	public Class returnedClass() {
		return clazz;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y || !(null == x || null == y) && x.equals(y);
	}
}
