var path = require('path'),
    rootPath = path.normalize(__dirname + '/..'),
    env = process.env.NODE_ENV || 'development';

var config = {
  development: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 6060,
    db: 'mongodb://192.168.99.100'
  },

  test: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 6060,
    db: 'mongodb://192.168.99.100'
  },

  production: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 6060,
    db: 'mongodb://192.168.99.100'
  }
};

module.exports = config[env];




/*
var config = {
  development: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 3000,
    db: 'mongodb://localhost/automatedtest-development'
  },

  */