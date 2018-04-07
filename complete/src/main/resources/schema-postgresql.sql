CREATE OR REPLACE VIEW PI.DUAL AS SELECT 1=1;

CREATE TABLE IF NOT EXISTS pi.macscan_map
(
    address VARCHAR(17) NOT NULL,
    owner VARCHAR NOT NULL,
    type VARCHAR
);

CREATE TABLE IF NOT EXISTS pi.macscan_results
(
    ts TIMESTAMP NOT NULL,
    address VARCHAR(17) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS i_macscan_results ON macscan_results (ts DESC, address);

CREATE OR REPLACE VIEW pi.v_mapscan AS  SELECT results.ts,
    results.address,
    _map.owner,
    _map.type
   FROM (macscan_results results
     LEFT JOIN macscan_map _map ON (((results.address)::text = (_map.address)::text)));

CREATE OR REPLACE VIEW pi.v_mapscan_home AS  SELECT results.ts,
    _map.owner
   FROM (macscan_results results
     LEFT JOIN macscan_map _map ON (((results.address)::text = (_map.address)::text)))
  WHERE ((_map.type)::text = ANY ((ARRAY['mobile'::character varying, 'laptop'::character varying, 'laptop_work'::character varying])::text[]));
