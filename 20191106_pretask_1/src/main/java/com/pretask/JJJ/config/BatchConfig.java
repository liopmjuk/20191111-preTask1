package com.pretask.JJJ.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.pretask.JJJ.Entity.BizSupportEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	private Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	@Value("classpath:data/사전과제1.csv")
	private Resource inputResource;
	
	private final String biz_sql = "insert into BIZ_SUPPORT (id, region_code, target, usage, bound, rate, institute, mgmt, reception, create_date, update_date) "
			+ "values(:id, :region, :target, :usage, :bound, :rate, :institute, :mgmt, :reception, :createDate, :updateDate)";
	
	@Bean
	public Job readCsvFileJob() {
		return jobBuilderFactory
				.get("readCsvFileJob")
				.incrementer(new RunIdIncrementer())
				.start(bizStep())
				.build();
	}
	
	@Bean
	public Step bizStep() {
		return stepBuilderFactory
				.get("bizStep")
				.<BizSupportEntity, BizSupportEntity>chunk(100)
				.reader(bizReader())
				.processor(bizProcessor())
				.writer(bizWriter())
				.build();
	}
	
	@Bean 
	public ItemProcessor<BizSupportEntity, BizSupportEntity> bizProcessor() {
		return new BizSupportProcessor();
	}
	
	@Bean
	public FlatFileItemReader<BizSupportEntity> bizReader() {
		FlatFileItemReader<BizSupportEntity> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(bizLineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(inputResource);
		return itemReader;
	}
	
	@Bean
	public LineMapper<BizSupportEntity> bizLineMapper() {
		DefaultLineMapper<BizSupportEntity> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		logger.debug("lineMapper");
		lineTokenizer.setNames(new String[] {"id", "region", "target", "usage", "bound", "rate", "institute", "mgmt", "reception"});
		lineTokenizer.setIncludedFields(0,1,2,3,4,5,6,7,8);
		BeanWrapperFieldSetMapper<BizSupportEntity> filedSetMapper = new BeanWrapperFieldSetMapper<>();
		filedSetMapper.setTargetType(BizSupportEntity.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(filedSetMapper);
		return lineMapper;
	}
	
	@Bean
	public JdbcBatchItemWriter<BizSupportEntity> bizWriter() {
		JdbcBatchItemWriter<BizSupportEntity> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(biz_sql);
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BizSupportEntity>());
		return itemWriter;
	}
}
