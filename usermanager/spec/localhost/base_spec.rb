require 'spec_helper'

describe file('/usr/local/tomcat/webapps/usermanager.war') do
  it { should be_file }
  it { should be_mode(644) }
end

# TODO: pre-expand the WAR file, no need to make the container do it on startup.
#describe file('/usr/local/tomcat/webapps/usermanager') do
#  it { should be_directory }
#  it { should be_mode(755) }
#end