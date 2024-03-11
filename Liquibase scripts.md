
delete table if 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

	// drop table 1, if it exists
	<changeSet author=".." id="..">
		<preConditions onFail="MARK_RAN">
		   <tableExists tableName="table_name"/> 
		</preConditions> 
		<dropTable tableName="table_name"/>
	</changeSet>

	// drop table 2, if it exists
	<changeSet author=".." id="..">
		<preConditions onFail="MARK_RAN">
		   <tableExists tableName="table_name"/>
		</preConditions> 
		<dropTable tableName="table_name"/>
	</changeSet>

	// ...

</databaseChangeLog>
```

create if not exist:
```xml
<changeSet author=".." id="..">
    <preConditions onFail="MARK_RAN">
        <not> <tableExists tableName="new_table_name"/> </not> 
    </preConditions> 
    
    <createTable tableName="new_table_name">
        <column name="id" type="${integer.type}"> 
            <constraints nullable="false"/>
        </column> 
        <column name="att" type="${integer.type}"/> 
    </createTable> 
</changeSet>
```


Reference: 
- preConditions: https://docs.liquibase.com/concepts/changelogs/preconditions.html 
- drop table : https://docs.liquibase.com/change-types/drop-table.html

