SELECT p.name, SUM(w.salary * DATEDIFF('MONTH', p.start_date, p.finish_date)) AS price
FROM project p
JOIN project_worker pw ON pw.project_id = p.id
JOIN worker w ON w.id = pw.worker_id
GROUP BY p.id, p.name
ORDER BY price DESC;
