var nameCollection = new NameCollection();
var appView = new AppView({ collection: nameCollection });
nameCollection.fetch().always(function() { appView.render(); });
