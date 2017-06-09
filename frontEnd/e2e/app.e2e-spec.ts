import { ErrorSimulatorPage } from './app.po';

describe('error-simulator App', () => {
  let page: ErrorSimulatorPage;

  beforeEach(() => {
    page = new ErrorSimulatorPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
