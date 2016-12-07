var Chance = require("chance");
var chance = new Chance();
var chai = require("chai");
chai.should();
chai.use(require('chai-things'));

describe("The /badges endpoint", function () {

    it("should allow an authenticated user to get a badges");
    it("should refuse to return a list of users to a user who is not authenticated");
});