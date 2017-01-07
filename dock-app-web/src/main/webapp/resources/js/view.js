var NameView = Backbone.View.extend({
  tagName: "tr",
  initialize: function() {
  },
  render: function() {
    var name = this.model.get("name");
    var updated = this.model.get("updated");
    this.$el.empty();
    this.$el.append("<td>" + name + "</td><td>" + updated + "</td>");
    
    return this;
  }
});


var AppView = Backbone.View.extend({
  el: '#content',
  initialize: function(){
    this.listenTo(this.collection, "sync", this.render);
  },
  render: function() {
    // Clear all elements in the table.
    this.$el.empty();
    
    // Create a new view for each model in the collection.
    this.collection.each(function(model) {
      var view = new NameView({ model: model });
      this.$el.append(view.render().$el);
    }, this);
    
    return this;
  }
});