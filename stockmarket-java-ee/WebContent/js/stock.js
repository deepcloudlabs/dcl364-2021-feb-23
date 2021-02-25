class StockViewModel {
    constructor() {
        this.symbol = ko.observable();
        this.company = ko.observable();
        this.description = ko.observable();
        this.price = ko.observable();
        this.stocks = ko.observableArray([]);
        this.stockLookup = {};
        this.toJson = () => {
            return JSON.stringify({
                symbol: this.symbol(),
                company: this.company(),
                description: this.description(),
                price: Number(this.price())
            });
        };
    }
   findStock() {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks/' + this.symbol(),
            success:  (stock) => {
                this.company(stock.company);
                this.price(stock.price);
                this.description(stock.description);
            }
        });
    };

    findAllStocks() {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=25',
            success: (stocks) => {
                stocks.map(stock => stock.newPrice = '');
                this.stocks(stocks);
            }
        });
    };

    removeStock(){
        $.ajax({
            method: 'DELETE',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks/' + this.symbol(),
            success: (stock) => {
                this.company(stock.company);
                this.price(stock.price);
            }
        });
    };

    addStock(){
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks',
            contentType: 'application/json',
            data: this.toJson(),
            success: (stock) => {
               console.log(stock)
            }
        });
    };

    updateStock() {
        $.ajax({
            method: 'PUT',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks',
            contentType: 'application/json',
            data: this.toJson(),
            success: (stock) => {
               console.log(stock);
            }
        });
    }
}    