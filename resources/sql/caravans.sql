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
   :price::DOUBLE PRECISION,
   :year::SMALLINT,
   :feet::SMALLINT,
   :tonne::DOUBLE PRECISION,
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
   :archived);

-- :name retrieve-caravans :? :*
-- :doc retrieves caravans
SELECT *
FROM caravans
WHERE caravans.archived = FALSE;

-- :name update-caravan! :! :n
-- :doc update an existing caravan record give the id
UPDATE caravans
SET
  type = :type,
  make = :make,
  model = :model,
  price = :price::DOUBLE PRECISION,
  year = :year::SMALLINT,
  feet = :feet::SMALLINT,
  tonne = :tonne::DOUBLE PRECISION,
  features = :features,
  photos = :photos,
  videos = :videos,
  axles = :axles,
  terrain = :terrain,
  bed = :bed,
  fridge = :fridge,
  frame = :frame,
  suspension = :suspension,
  condition = :condition,
  vin = :vin,
  archived = :archived
WHERE id = :id::UUID;

-- :name delete-caravan! :! :n
-- :doc delete a caravan record given the id
UPDATE caravans
SET archived = TRUE
WHERE id = :id::UUID;
