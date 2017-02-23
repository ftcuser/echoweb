require 'spec_helper'

describe file('/usr/local/tomcat/webapps/usermanager.war') do
  it { should be_file }
  it { should be_mode(644) }
end

describe file('/usr/local/tomcat/webapps/usermanager') do
  it { should be_directory }
  it { should be_mode(755) }
end