define("private-repositories",["jquery","UsersTable"],function($,UsersTable){var constr=function(){this.initialize()};_.extend(constr.prototype,{initialize:function(){var table=new UsersTable;table.render();$("body").prepend(table.el)}});return constr});AJS.$(document).ready(function($){require(["private-repositories"],function(PrivateRepos){var privateRepos=new PrivateRepos})}(AJS.$));define("Group",["backbone"],function(Backbone){return Backbone.Model.extend({})});define("Groups",["Backbone","Group"],function(Backbone,Group){return Backbone.Collection.extend({model:Group})});define("User",["backbone"],function(Backbone){return Backbone.Model.extend({})});define("Users",["Backbone","User"],function(Backbone,User){return Backbone.Collection.extend({model:User})});define("GroupRow",["backbone"],function(Backbone){return Backbone.View.extend({events:{"click .delete":"onDelete"},onDelete:function(e){},render:function(){}})});define("UsersTable",["Table","GroupRow"],function(Table,GroupRow){return Table.extend({itemView:GroupRow})});define("Table",["backbone","UserRowView"],function(Backbone){return Backbone.View.extend({initialize:function(){},addChildView:function(model){this.el.append(new this.itemView({model:model}).render())},render:function(){return this}})});define("UserRow",["backbone"],function(Backbone){return Backbone.View.extend({events:{"click .delete":"onDelete"},onDelete:function(e){},render:function(){}})});define("UsersTable",["Table","UserRow"],function(Table,UserRow){return Table.extend({itemView:UserRow})});