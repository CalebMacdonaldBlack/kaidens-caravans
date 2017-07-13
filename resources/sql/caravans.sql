-- :name create-caravan! :! n
-- :doc creates a new caravan record
INSERT INTO caravans
(type, make, model, price, year, feet, tonne, features, photos, videos)
VALUES (:type, :make, :model, :price, :year, :feet, :tonne, :features, :photos, :videos);

-- :name retrieve-caravans :? :*
-- :doc retrieves caravans
SELECT * FROM caravans
