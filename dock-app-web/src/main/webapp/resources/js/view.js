var NameView = Backbone.View.extend({
  tagName: "tr",
  initialize: function() {
  },
  render: function() {
    var name = this.model.get("name");
    var updated = this.model.get("updated");
    this.$el.empty();
    this.$el.append("<td><span class='edit-btn'/></td><td><input class='name-box' type='text' disabled='yes' value='" + name + "'/></td>");
    this.$el.append("<td>" + updated + "</td>");
    
    return this;
  },
  events: {
    "click .edit-btn" : "allowEdit",
    "focus .name-box" : "textBoxFocus",
    "blur .name-box" : "textBoxBlur",
    "keyup .name-box" : "checkEnter"
  },
  // This function handles the enabling of editting on the name text box.
  allowEdit: function() {
    var input = this.$el.find(".name-box");
    input.attr("disabled", false);
    input.focus();
  },
  // Handle the focus of the text box.
  textBoxFocus: function(event) {
    var textbox = $(event.target);
    textbox.val(textbox.val());
  },
  // When focus leaves the text box.
  textBoxBlur: function(event) {
    var textbox = $(event.target);
    textbox.attr("disabled", true);
  },
  // If enter is pressed exit the editing of the text box.
  checkEnter: function(event) {
    var textbox = $(event.target);
    if (event.keyCode == 13) { textbox.blur(); }
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