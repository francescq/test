package com.francescq.test.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class Token implements ResultSetMapper<Token> {

    private String id;
    private String type;
    private String content;
    private Timestamp dateExpiry;

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String CONTENT = "content";
    public static final String DATE_EXPIRY = "date_expiry";

    public Token() {
    }

    public Token(String id, String type, String content, Timestamp date_expiry) {
	this.id = id;
	this.type = type;
	this.content = content;
	this.dateExpiry = date_expiry;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Timestamp getDate_expiry() {
	return dateExpiry;
    }

    public void setDate_expiry(Timestamp date_expiry) {
	this.dateExpiry = date_expiry;
    }

    public Token map(int index, ResultSet r, StatementContext ctx) throws SQLException {
	Token token = new Token(r.getString(ID), r.getString(TYPE), r.getString(CONTENT), r.getTimestamp(DATE_EXPIRY));
	return token;
    }
}