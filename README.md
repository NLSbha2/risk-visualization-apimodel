# risk-visualization-apimodel

The Risk Visualization API model is the API to retrieve the Risk relationships for organizations within Atradius. 
The API implements a GET request for   /v1/sc/org-data/risk-data-api with query parameters. More information on the contract can be found [here](src/main/specs/apiblueprint-riskvisualization.md)

The API model generates a JAR in Atradius Articatory. To use it include the latest JAR into the dependencies of your project.  