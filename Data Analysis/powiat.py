import numpy as np
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
#from sklearn.pipeline import make_pipeline

class Powiat:
    def __init__(self, id, name, voivodeship, population, natural_growth, prediction, last_updated):
        self.id = id
        self.name = name
        self.voivodeship = voivodeship
        self.population = population
        self.natural_growth = natural_growth
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
    # Model accuracy: 0.97
    def predict_population(self):
        '''Predicts the population using polynomial regression. Takes only population and natural growth into consideration, WIP'''
        years = list(self.population.keys())
        years = [int(year) for year in years]
        years = np.array(years).reshape(-1, 1)
        y = np.array(list(self.population.values()))
        natural_growth = list(self.natural_growth.values())
        # Natural growth is not updated by GUS as fast as population.
        # Currently it will take last instance of natural growth, but in future it will try to estimate its value.
        while True:
            if len(natural_growth) != len(years):
                natural_growth.append(natural_growth[-1])
            else:
                break

        natural_growth = np.array(natural_growth).reshape(-1, 1)

        poly_features = PolynomialFeatures(degree=2)
        X_poly = poly_features.fit_transform(np.concatenate((years, natural_growth), axis=1))

        model = LinearRegression()
        model.fit(X_poly, y)

        prediction_data = np.array([[years[-1][0] +1, natural_growth[-1][0]]])
        prediction_data_poly = poly_features.transform(prediction_data)
        prediction = model.predict(prediction_data_poly)

        r_squared = model.score(X_poly, y)

        return round(prediction[0]), r_squared
    

    # ---------------------OLD MODEL DO NOT USE---------------------
    # Model accuracy: 0.93
    # def predict_population(self):
    #     '''Predicts the population using polynomial regression. Takes only population into consideration, WIP'''
    #     X = list(self.population.keys())
    #     X = [int(year) for year in X]
    #     X = np.array(X).reshape(-1, 1)
    #     y = np.array(list(self.population.values()))

    #     model = make_pipeline(PolynomialFeatures(degree=4), LinearRegression(fit_intercept=False))
    #     model.fit(X, y)

    #     prediction_year = np.array([X[-1]+1])
    #     prediction = model.predict(prediction_year)

    #     r_squared = model.score(X, y)

    #     return round(prediction[0]), r_squared
    # ---------------------OLD MODEL DO NOT USE---------------------