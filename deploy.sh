docker-compose stop
docker-compose down

cd kotlin-react
./deploy.sh
cd ../portfolio
./deploy.sh
cd ..

docker-compose up
