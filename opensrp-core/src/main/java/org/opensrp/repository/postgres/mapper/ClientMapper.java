package org.opensrp.repository.postgres.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.opensrp.domain.postgres.Client;
import org.opensrp.domain.postgres.ClientExample;

public interface ClientMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	long countByExample(ClientExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int deleteByExample(ClientExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int insert(Client record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int insertSelective(Client record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	List<Client> selectByExample(ClientExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	Client selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int updateByExampleSelective(@Param("record") Client record, @Param("example") ClientExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int updateByExample(@Param("record") Client record, @Param("example") ClientExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int updateByPrimaryKeySelective(Client record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client
	 * @mbg.generated  Fri Apr 06 16:01:06 EAT 2018
	 */
	int updateByPrimaryKey(Client record);
}