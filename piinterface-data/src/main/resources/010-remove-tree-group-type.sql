UPDATE pin_group
SET type_id = ( SELECT MAX(id) FROM pin_group_type WHERE name = 'SEQUENTIAL' )
WHERE type_id = ( SELECT MAX(id) FROM pin_group_type WHERE name = 'TREE' )
;

DELETE FROM pin_group_type WHERE name = 'TREE';

COMMIT;