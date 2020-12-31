./gradlew clean
./gradlew browserWebpack

cp build/distributions/kotlin-react.js ../portfolio/src/main/resources/static
cp build/distributions/kotlin-react.js.map ../portfolio/src/main/resources/static



