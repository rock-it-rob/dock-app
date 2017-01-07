/**
 * Models
 */
var NameEntry = Backbone.Model.extend({
 defaults: {
   name: null,
   updated: null
 },
 idAttribute: "name"
});


/**
 * Collections
 */
var NameCollection = Backbone.Collection.extend({
 model: NameEntry,
 url: "http://localhost:8080/dockapp/nameentry/"
});