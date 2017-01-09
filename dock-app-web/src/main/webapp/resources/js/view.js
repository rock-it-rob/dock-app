var NameView = Backbone.View.extend({
  tagName: "tr",
  initialize: function() {
    this.listenTo(this.model, "sync", this.render);
    this.listenTo(this.model, "error", this.render);
    this.listenTo(this.model, "change", this.updateModel);
  },
  render: function() {
    var name = this.model.get("name");
    var updated = this.model.get("updated");
    this.$el.empty();
    this.$el.append("<td><span class='edit-btn'/></td><td><input class='name-box' type='text' disabled='yes' value='" + name + "'/></td>");
    this.$el.append("<td>" + updated + "</td>");
    
    alert("rendered");
    
    return this;
  },
  events: {
    "click .edit-btn" : "allowEdit",
    "keyup .name-box" : "checkEnter",
    "blur .name-box" : "updateName"
  },
  // This function handles the enabling of editting on the name text box.
  allowEdit: function() {
    var input = this.$el.find(".name-box");
    input.attr("disabled", false);
    input.focus();
    input.val(input.val());
  },
  // When focus leaves the text box update the model.
  updateName: function() {
    var textbox = this.$el.find(".name-box");
    this.model.set({ name: textbox.val() });
  },
  // Syncrhonize the model with the server.
  updateModel: function() {
    this.model.save(this.model.attributes, { error: this.badUpdate });
  },
  // If enter is pressed exit the editing of the text box.
  checkEnter: function(event) {
    if (event.keyCode == 13)
    {
      $(event.target).attr("disabled", true);
    }
  },
  // Show an error if the update fails.
  badUpdate: function(model, response, options)
  {
    //alert("Could not update name");
    alert(response.statusText);
    //
    //
    // PUT AN ERROR MESSAGE SOMEWHERE
    //
    //
    //
    //
    
    // Reset the model which should re-render the view.
    model.fetch();
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