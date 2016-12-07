var path = require('path'),
    rootPath = path.normalize(__dirname + '/..'),
    env = process.env.NODE_ENV || 'development';

var config = {
  development: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 3000,
    db: 'mongodb://localhost/automatedtest-development'
  },

  test: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 3000,
    db: 'mongodb://localhost/automatedtest-test'
  },

  production: {
    root: rootPath,
    app: {
      name: 'automatedtest'
    },
    port: process.env.PORT || 3000,
    db: 'mongodb://localhost/automatedtest-production'
  }
};

module.exports = config[env];
