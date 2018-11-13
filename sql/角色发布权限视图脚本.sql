CREATE OR REPLACE VIEW V_ROLE_PUB_PERMISSION
AS
SELECT
        GET_UUID() AS ID  ,
        S."ROLE_ID"       ,
        S."ROLE_NAME"     ,
        S."ROLE_TYPE"     ,
        S."START_TIME"    ,
        S."DUE_TIME"      ,
        S."OPERATION_ID"  ,
        S."OPERATION_CODE",
        S."OPERATION_NAME",
        S."RESOURCE_ID"   ,
        S."RESOURCE_NAME" ,
        S."RESOURCE_TYPE_ID",
        S."NATIVE_RESOURCE_ID"
FROM
        (
                SELECT DISTINCT
                        R.ID     AS ROLE_ID      ,
                        R.NAME   AS ROLE_NAME    ,
                        R."TYPE" AS ROLE_TYPE    ,
                        AL.START_TIME            ,
                        AL.DUE_TIME              ,
                        AP.ID   AS OPERATION_ID  ,
                        AP.CODE AS OPERATION_CODE,
                        AP.NAME AS OPERATION_NAME,
                        AR.ID   AS RESOURCE_ID   ,
                        AR.NAME AS RESOURCE_NAME ,
                        AR.ACL_RES_TYPE_ID AS RESOURCE_TYPE_ID,
                        AR.NATIVE_RESOURCE_ID
                FROM
                        CS_ROLE              AS R
                INNER JOIN CS_PUB_PERMISSION AS AC
                ON
                        AC.SECURITY_ROLE_ID = R.ID
                INNER JOIN CS_ACL_PERMISSION AS AL
                ON
                        (
                                AC.ACL_PERMISSION_ID = AL.ID
                            AND AL.REMOVED           = 'N'
                        )
                INNER JOIN CS_ACL_OPERATION AS AP
                ON
                        (
                                AL.ACL_OPERATION_ID = AP.ID
                            AND AP.REMOVED          = 'N'
                        )
                INNER JOIN CS_ACL_RESOURCE AS AR
                ON
                        (
                                AL.ACL_RESOURCE_ID = AR.ID
                            AND AR.REMOVED         = 'N'
                        )
                WHERE
                        R.REMOVED = 'N'
        ) AS S;