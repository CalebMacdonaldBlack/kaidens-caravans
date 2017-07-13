-- :name create-caravan! :! n
-- :doc creates a new caravan record
INSERT INTO caravans
(type, make, model, price, year, feet, tonne, features, photos, videos)
VALUES (:type, :make, :model, :price, :year, :feet, :tonne, :features, :photos, :videos);

-- :name retrieve-caravans :? :*
-- :doc retrieves caravans
SELECT *
FROM caravans;

-- :name update-caravans! :! :n
-- :doc update an existing caravan record
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
WHERE id = :id;
