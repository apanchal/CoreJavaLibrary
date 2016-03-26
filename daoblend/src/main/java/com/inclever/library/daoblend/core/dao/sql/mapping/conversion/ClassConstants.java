package com.inclever.library.daoblend.core.dao.sql.mapping.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

@SuppressWarnings("rawtypes")
public interface ClassConstants {

    public static final Class ABYTE = Byte[].class;
    public static final Class AOBJECT = Object[].class;
    public static final Class ACHAR = Character[].class;
    public static final Class APBYTE = byte[].class;
    public static final Class APCHAR = char[].class;
    public static final Class BIGDECIMAL = BigDecimal.class;
    public static final Class BIGINTEGER = BigInteger.class;
    public static final Class BOOLEAN = Boolean.class;
    public static final Class BYTE = Byte.class;
    public static final Class CLASS = Class.class;
    public static final Class CHAR = Character.class;
    public static final Class CALENDAR = Calendar.class;
    public static final Class DOUBLE = Double.class;
    public static final Class FLOAT = Float.class;
    public static final Class GREGORIAN_CALENDAR = GregorianCalendar.class;
    public static final Class INTEGER = Integer.class;
    public static final Class LONG = Long.class;
    public static final Class NUMBER = Number.class;
    public static final Class OBJECT = Object.class;
    public static final Class PBOOLEAN = boolean.class;
    public static final Class PBYTE = byte.class;
    public static final Class PCHAR = char.class;
    public static final Class PDOUBLE = double.class;
    public static final Class PFLOAT = float.class;
    public static final Class PINT = int.class;
    public static final Class PLONG = long.class;
    public static final Class PSHORT = short.class;
    public static final Class SHORT = Short.class;
    public static final Class SQLDATE = java.sql.Date.class;
    public static final Class STRING = String.class;
    public static final Class TIME = java.sql.Time.class;
    public static final Class TIMESTAMP = java.sql.Timestamp.class;
    public static final Class UTILDATE = java.util.Date.class;
    public static final Class XML_GREGORIAN_CALENDAR = XMLGregorianCalendar.class;

    public static final Class Collection_Class = Collection.class;
    public static final Class List_Class = List.class;

    public static final Class Map_Class = Map.class;

    public static final Class Set_Class = Set.class;
    public static final Class URL_Class = URL.class;

    // LOB support types
    public static final Class BLOB = java.sql.Blob.class;
    public static final Class CLOB = java.sql.Clob.class;

    // Indication to DaoConversionManager not to convert classes implementing
    // this interface
    public static final Class NOCONVERSION = NoConversion.class;
}
