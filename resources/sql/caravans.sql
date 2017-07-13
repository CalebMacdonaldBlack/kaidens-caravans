-- :name create-caravan! :! n
-- :doc creates a new caravan record
INSERT INTO caravans
(type, make, model, price, year, feet, tonne, features, photos, videos)
VALUES (:type, :make, :model, :price, :year, :feet, :tonne, :features, :photos, :videos);

-- :name retrieve-caravans :? :*
-- :doc retrieves caravans
SELECT *
FROM caravans;

-- :name update-caravan! :! :n
-- :doc update an existing caravan record give the id
UPDATE caravans
SET
  type = :type,
  make = :make,
  model = :model,
  price = :price,
  year = :year,
  feet = :feet,
  tonne = :tonne,
  features = :features,
  photos = :photos,
  videos = :videos
WHERE id = :id::UUID;

-- :name delete-caravan! :! :n
-- :doc delete a caravan record given the id
UPDATE caravans
SET archived = TRUE
WHERE id = :id;
