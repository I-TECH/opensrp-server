package org.opensrp.repository.postgres.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.opensrp.domain.postgres.StructureMetadata;
import org.opensrp.domain.postgres.StructureMetadataExample;

public interface StructureMetadataMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	long countByExample(StructureMetadataExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int deleteByExample(StructureMetadataExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int insert(StructureMetadata record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int insertSelective(StructureMetadata record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	List<StructureMetadata> selectByExample(StructureMetadataExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	StructureMetadata selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int updateByExampleSelective(@Param("record") StructureMetadata record,
			@Param("example") StructureMetadataExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int updateByExample(@Param("record") StructureMetadata record, @Param("example") StructureMetadataExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int updateByPrimaryKeySelective(StructureMetadata record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.structure_metadata
	 * @mbg.generated  Tue Dec 04 16:50:28 EAT 2018
	 */
	int updateByPrimaryKey(StructureMetadata record);
}