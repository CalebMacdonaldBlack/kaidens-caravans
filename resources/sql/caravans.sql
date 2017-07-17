-- :name create-caravan! :! n
-- :doc creates a new caravan record
INSERT INTO caravans
(type,
 make,
 model,
 price,
 year,
 feet,
 tonne,
 features,
 photos,
 videos,
 axles,
 terrain,
 bed,
 fridge,
 frame,
 suspension,
 condition,
 vin,
 archived)
VALUES
  (:type,
    :make,
    :model,
    :price :: DOUBLE PRECISION,
    :year :: SMALLINT,
    :feet :: SMALLINT,
    :tonne :: DOUBLE PRECISION,
    :features,
    :photos,
    :videos,
    :axles,
   :terrain,
   :bed,
   :fridge,
   :frame,
   :suspension,
   :condition,
   :vin,
   :archived :: BOOLEAN);

-- :name retrieve-caravans :? :*
-- :doc retrieves caravans
SELECT *
FROM caravans
WHERE COALESCE(type, '') ILIKE /*~ (str "'%" (:type params) "%'") ~*/ AND
      COALESCE(make, '') ILIKE /*~ (str "'%" (:make params) "%'") ~*/ AND
      COALESCE(model, '') ILIKE /*~ (str "'%" (:model params) "%'") ~*/ AND
      COALESCE(price :: VARCHAR, '') LIKE /*~ (str "'%" (:price params) "%'") ~*/ AND
      COALESCE(year :: VARCHAR, '') LIKE /*~ (str "'%" (:year params) "%'") ~*/ AND
      COALESCE(feet :: VARCHAR, '') LIKE /*~ (str "'%" (:feet params) "%'") ~*/ AND
      COALESCE(tonne :: VARCHAR, '') LIKE /*~ (str "'%" (:tonne params) "%'") ~*/ AND
      COALESCE(axles :: VARCHAR, '') LIKE /*~ (str "'%" (:axles params) "%'") ~*/ AND
      COALESCE(terrain, '') ILIKE /*~ (str "'%" (:terrain params) "%'") ~*/ AND
      COALESCE(bed, '') ILIKE /*~ (str "'%" (:bed params) "%'") ~*/ AND
      COALESCE(fridge, '') ILIKE /*~ (str "'%" (:fridge params) "%'") ~*/ AND
      COALESCE(frame, '') ILIKE /*~ (str "'%" (:frame params) "%'") ~*/ AND
      COALESCE(suspension, '') ILIKE /*~ (str "'%" (:suspension params) "%'") ~*/ AND
      COALESCE(condition, '') ILIKE /*~ (str "'%" (:condition params) "%'") ~*/ AND
      COALESCE(vin, '') ILIKE /*~ (str "'%" (:vin params) "%'") ~*/ AND
      COALESCE(archived :: VARCHAR, '') ILIKE /*~ (str "'%" (:archived params) "%'") ~*/;

-- :name update-caravan! :! :n
-- :doc update an existing caravan record give the id
UPDATE caravans
SET
  type       = :type,
  make       = :make,
  model      = :model,
  price      = :price :: DOUBLE PRECISION,
  year       = :year :: SMALLINT,
  feet       = :feet :: SMALLINT,
  tonne      = :tonne :: DOUBLE PRECISION,
  features   = :features,
  photos     = :photos,
  videos     = :videos,
  axles      = :axles,
  terrain    = :terrain,
  bed        = :bed,
  fridge     = :fridge,
  frame      = :frame,
  suspension = :suspension,
  condition  = :condition,
  vin        = :vin,
  archived   = :archived :: BOOLEAN
WHERE id = :id :: UUID;

-- :name delete-caravan! :! :n
-- :doc delete a caravan record given the id
UPDATE caravans
SET archived = TRUE
WHERE id = :id :: UUID;

-- :name search-type :? :*
-- :doc retrieves a distinct list of the type column
SELECT DISTINCT type FROM caravans WHERE type ILIKE CONCAT('%',:query ,'%');

-- :name search-make :? :*
-- :doc retrieves a distinct list of the make column
SELECT DISTINCT make FROM caravans WHERE make ILIKE CONCAT('%',:query ,'%');

-- :name search-model :? :*
-- :doc retrieves a distinct list of the model column
SELECT DISTINCT model FROM caravans WHERE model ILIKE CONCAT('%',:query ,'%');

-- :name search-condition :? :*
-- :doc retrieves a distinct list of the condition column
SELECT DISTINCT condition FROM caravans WHERE condition ILIKE CONCAT('%',:query ,'%');

