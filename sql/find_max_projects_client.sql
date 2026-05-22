SELECT c.name, COUNT(p.id) AS project_count
FROM client c
JOIN project p ON p.client_id = c.id
GROUP BY c.id, c.name
HAVING COUNT(p.id) = (
    SELECT MAX(project_count)
    FROM (SELECT COUNT(*) AS project_count FROM project GROUP BY client_id) counts
)
ORDER BY c.name;
