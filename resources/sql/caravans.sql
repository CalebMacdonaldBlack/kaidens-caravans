-- :name create-caravan! :! n
-- :doc creates a new caravan record
INSERT INTO caravans
(type, make, model, price, year, feet, tonne, features, photos, video)
VALUES (:type, :make, :model, :price, :year, :feet, :tonne, :features, :photos, :video);