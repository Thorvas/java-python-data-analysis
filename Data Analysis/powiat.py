import numpy as np
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn.pipeline import make_pipeline

class Powiat:
    def __init__(self, id, name, voivodeship, population, prediction, last_updated):
        self.id = id
        self.name = name
        self.voivodeship = voivodeship
        self.population = population
        self.prediction = prediction
        self.last_updated = last_updated


    def asdict(self):
        return{
                'id': self.id,
                'name': self.name, 
                'voivodeship': self.voivodeship,
                'population': self.population,
                'prediction': self.prediction, 
                'last_updated': self.last_updated
                }
    
    def predict_population(self):
        '''Predicts the population using polynomial regression. Takes only population into consideration, WIP'''
        X = list(self.population.keys())
        X = [int(year) for year in X]
        X = np.array(X).reshape(-1, 1)
        y = list(self.population.values())

        model = make_pipeline(PolynomialFeatures(degree=2), LinearRegression(fit_intercept=False))
        model.fit(X, y)

        prediction_year = np.array([X[-1]+1])
        prediction = model.predict(prediction_year)

        return round(prediction[0])
