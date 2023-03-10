SELECT 
CONCAT ('Project  ', ID)
AS NAME,
DATEDIFF(month, START_DATE, FINISH_DATE) 
AS MONTH_COUNT
FROM PROJECT
WHERE
DATEDIFF(month, START_DATE, FINISH_DATE) IN (
SELECT MAX
(DATEDIFF(month, START_DATE, FINISH_DATE))
FROM PROJECT);