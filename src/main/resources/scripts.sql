CREATE TABLE sum_table(
    adder1 bigint NOT NULL,
    adder2 bigint NOT NULL,
    summ bigint NOT NULL
);

CREATE OR ALTER FUNCTION insertSum(
    in value1 bigint,
    in value2 bigint
) RETURNS SETOF sum_table
AS $$
declare
	summValue bigint default value1 + value2;
	r sum_table%rowtype;
begin
	insert into sum_table(adder1, adder2, summ)
	values(value1, value2, summValue)
	returning * into r;
	return next r;
end;
$$ language plpgsql VOLATILE;