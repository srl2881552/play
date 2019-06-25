package com.song.play.cloud.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
public interface RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException;
}
